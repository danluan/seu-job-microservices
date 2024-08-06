package br.com.danluan.user.service.impl;

import br.com.danluan.user.exception.UserIdAlreadyInUseException;
import br.com.danluan.user.exception.UserNotFoundException;
import br.com.danluan.user.exception.WorkerNotFoundException;
import br.com.danluan.user.feignclients.ApplicationFeignClient;
import br.com.danluan.user.model.Resume;
import br.com.danluan.user.model.User;
import br.com.danluan.user.model.Worker;
import br.com.danluan.user.model.dto.ApplicationDTO;
import br.com.danluan.user.model.dto.ResumeUpdateDTO;
import br.com.danluan.user.model.dto.WorkerDTO;
import br.com.danluan.user.repository.UserRepository;
import br.com.danluan.user.repository.WorkerRepository;
import br.com.danluan.user.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkerServiceImpl implements WorkerService {
    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ApplicationFeignClient applicationFeignClient;

    @Override
    public List<WorkerDTO> getAllWorkers() {
        List<Worker> workers = workerRepository.findAll();
        return workers.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Worker getWorkerById(Integer id) {
        return workerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Worker not found for ID: " + id));
    }

    @Override
    public WorkerDTO getWorkerDTOById(Integer id) {
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Worker not found for ID: " + id));
        return toDTO(worker);
    }

    @Override
    public WorkerDTO createWorker(WorkerDTO workerDTO) {
        if(workerRepository.findByUserId(workerDTO.getUserId()).isPresent()) {
            throw new UserIdAlreadyInUseException();
        }
        User user = userService.getUserById(workerDTO.getUserId());

        if(user == null) {
            throw new UserNotFoundException();
        }

        Worker worker = new Worker();

        worker.setUser(user);

        return this.toDTO(workerRepository.save(worker));

    }

    @Override
    public WorkerDTO updateWorker(WorkerDTO workerDTO) {
        Worker worker = workerRepository.findById(workerDTO.getId()).orElse(null);
        if (worker == null) {
            throw new WorkerNotFoundException();
        }
        return toDTO(worker);
    }

    @Override
    public void deleteWorker(Integer id) {
        workerRepository.findById(id).orElseThrow(WorkerNotFoundException::new);
        workerRepository.deleteById(id);
    }

    @Override
    public void updateWorkerResume(Integer id, Resume resume) {
        Worker user = workerRepository.findById(id).orElse(null);
        assert user != null;
        user.setResume(resume);
        workerRepository.save(user);
    }

    @Override
    public WorkerDTO toDTO (Worker worker) {
        WorkerDTO workerDTO = new WorkerDTO();
        workerDTO.setId(worker.getId());
        workerDTO.setUserId(worker.getUser().getId());
        workerDTO.setName(worker.getUser().getName());
        workerDTO.setEmail(worker.getUser().getEmail());
        workerDTO.setLogin(worker.getUser().getLogin());
        workerDTO.setPhone(worker.getUser().getPhoneNumber());

        List<ApplicationDTO> applicationsByWorker = applicationFeignClient.getAllApplicationsByWorkerId(worker.getId()).getBody();

        if (applicationsByWorker != null) {
            workerDTO.setApplications(applicationsByWorker.stream().map((application -> {
                ApplicationDTO applicationDTO = new ApplicationDTO();
                applicationDTO.setId(application.getId());
                applicationDTO.setWorkerId(application.getWorkerId());
                    applicationDTO.setJobId(application.getJobId());
                applicationDTO.setStatus(application.getStatus());
                applicationDTO.setDateApply(application.getDateApply());
                return applicationDTO;
            })).collect(Collectors.toList()));
        }
        workerDTO.setResume(
                worker.getResume() != null ? new ResumeUpdateDTO(worker.getResume()) : null
        );
        return workerDTO;
    }

    public Worker toEntity (WorkerDTO workerDTO) {
        Worker worker = new Worker();
        worker.setId(workerDTO.getId());
        worker.getUser().setName(workerDTO.getName());
        worker.getUser().setEmail(workerDTO.getEmail());
        worker.getUser().setLogin(workerDTO.getLogin());
        worker.getUser().setPhoneNumber(workerDTO.getPhone());
        worker.setResume(new Resume(workerDTO.getResume().getExperience(), workerDTO.getResume().getEducation(), workerDTO.getResume().getSkills()));

        return worker;
    }
}