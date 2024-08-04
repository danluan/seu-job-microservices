package br.com.danluan.user.service;

import br.com.danluan.user.model.Resume;
import br.com.danluan.user.model.dto.ResumeDTO;
import br.com.danluan.user.model.dto.ResumeUpdateDTO;

import java.util.List;

public interface ResumeService {
    List<ResumeDTO> getAllResumes();
    ResumeDTO getResumeById(Integer id);
    ResumeDTO createResume(ResumeDTO resumeDTO);
    ResumeDTO updateResume(Integer id, ResumeUpdateDTO resumeDTO);
    void deleteResume(Integer id);
    ResumeDTO toDto(Resume resume);
    Resume toEntity(ResumeDTO resumeDTO);
}
