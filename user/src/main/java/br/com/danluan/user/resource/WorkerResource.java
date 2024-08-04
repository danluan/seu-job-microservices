package br.com.danluan.user.resource;

import br.com.danluan.user.exception.UserIdAlreadyInUseException;
import br.com.danluan.user.exception.UserNotFoundException;
import br.com.danluan.user.exception.WorkerIdAlreadyInUseException;
import br.com.danluan.user.exception.WorkerNotFoundException;
import br.com.danluan.user.model.dto.WorkerDTO;
import br.com.danluan.user.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RefreshScope
@RestController
@RequiredArgsConstructor
@RequestMapping("/worker")
public class WorkerResource {

    @Autowired
    private WorkerService workerService;

    @GetMapping
    public List<WorkerDTO> getWorkers() {
        return workerService.getAllWorkers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkerDTO> getWorkerById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(workerService.getWorkerDTOById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<WorkerDTO> createWorker(@RequestBody @Valid WorkerDTO workerDTO) {
        try {
            WorkerDTO createdWorker = workerService.createWorker(workerDTO);
            return new ResponseEntity<>(createdWorker, HttpStatus.CREATED);
        } catch (UserIdAlreadyInUseException | WorkerIdAlreadyInUseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<WorkerDTO> updateWorker(@RequestBody @Valid WorkerDTO workerDTO) {
        try {
            WorkerDTO workerUpdated = workerService.updateWorker(workerDTO);
            return new ResponseEntity<>(workerUpdated, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorker(@PathVariable Integer id) {
        try {
            workerService.deleteWorker(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (WorkerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}