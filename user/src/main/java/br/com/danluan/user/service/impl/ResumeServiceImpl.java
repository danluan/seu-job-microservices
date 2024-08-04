package br.com.danluan.user.service.impl;

import br.com.danluan.user.exception.WorkerIdAlreadyInUseException;
import br.com.danluan.user.model.Resume;
import br.com.danluan.user.model.Worker;
import br.com.danluan.user.model.dto.ResumeDTO;
import br.com.danluan.user.model.dto.ResumeUpdateDTO;
import br.com.danluan.user.repository.ResumeRepository;
import br.com.danluan.user.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private WorkerServiceImpl workerService;

    @Override
    public List<ResumeDTO> getAllResumes() {
        List<Resume> resumes = resumeRepository.findAll();
        return resumes.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public ResumeDTO getResumeById(Integer id) {
        Resume resume = resumeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Resume not found for ID: " + id));
        return toDto(resume);
    }

    @Override
    public ResumeDTO createResume(ResumeDTO resumeDTO) {
        if (resumeRepository.findByWorkerId(resumeDTO.getWorkerId()).isPresent()) {
            throw new WorkerIdAlreadyInUseException();
        }
        Resume resume = this.toEntity(resumeDTO);
        Worker worker = workerService.getWorkerById(resumeDTO.getWorkerId());
        resume.setWorker(worker);
        worker.setResume(resume);
        workerService.updateWorker(workerService.toDTO(worker));

        resumeRepository.save(resume);
        return this.toDto(resume);
    }

    @Override
    public ResumeDTO updateResume(Integer id, ResumeUpdateDTO resumeDTO) {
        Resume resume = resumeRepository.findById(id).orElse(null);
        if (resume == null) {
            return null;
        }

        resume.setSkills(resumeDTO.getSkills());
        resume.setExperiences(resumeDTO.getExperience());
        resume.setEducation(resumeDTO.getEducation());

        return this.toDto(resumeRepository.save(resume));
    }

    @Override
    public void deleteResume(Integer id) {
        resumeRepository.deleteById(id);
    }

    @Override
    public ResumeDTO toDto(Resume resume) {
        ResumeDTO resumeDTO = new ResumeDTO(resume);
        resumeDTO.setWorkerId(resume.getWorker().getId());
        return resumeDTO;
    }

    @Override
    public Resume toEntity(ResumeDTO resumeDTO) {
        Resume resume = new Resume();
        resume.setId(resumeDTO.getId());
        resume.setEducation(resumeDTO.getEducation());
        resume.setExperiences(resumeDTO.getExperience());
        resume.setSkills(resumeDTO.getSkills());
        Worker worker = workerService.getWorkerById(resumeDTO.getWorkerId());
        resume.setWorker(worker);
        return resume;
    }
}
