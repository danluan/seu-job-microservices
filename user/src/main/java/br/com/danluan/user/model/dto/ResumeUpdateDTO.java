package br.com.danluan.user.model.dto;

import br.com.danluan.user.model.Resume;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResumeUpdateDTO {
    private Integer id;
    private String education;
    private String experience;
    private String skills;

    public ResumeUpdateDTO(Resume resume) {
        this.id = resume.getId();
        this.education = resume.getEducation();
        this.experience = resume.getExperiences();
        this.skills = resume.getSkills();
    }

    public ResumeUpdateDTO(String experiences, String education, String skills) {
        this.experience = experiences;
        this.education = education;
        this.skills = skills;
    }
}
