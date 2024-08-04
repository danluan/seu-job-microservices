package br.com.danluan.user.service;

import br.com.danluan.user.model.Resume;
import br.com.danluan.user.model.Worker;
import br.com.danluan.user.model.dto.WorkerDTO;

import java.util.List;

public interface WorkerService {
    WorkerDTO toDTO(Worker worker);
    Worker toEntity(WorkerDTO workerDTO);
    List<WorkerDTO> getAllWorkers();
    WorkerDTO getWorkerDTOById(Integer id);
    Worker getWorkerById(Integer id);
    WorkerDTO createWorker(WorkerDTO workerDTO);
    WorkerDTO updateWorker(WorkerDTO workerDTO);
    void deleteWorker(Integer id);
    void updateWorkerResume(Integer id, Resume resume);
}
