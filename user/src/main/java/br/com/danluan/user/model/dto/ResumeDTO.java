package br.com.danluan.user.model.dto;

import br.com.danluan.user.model.Resume;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDTO {
    private Integer id;
    private Integer workerId;
    private String education;
    private String experience;
    private String skills;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    private Date creationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    private Date updateDate;

    public ResumeDTO(Resume resume) {
        this.id = resume.getId();
        this.education = resume.getEducation();
        this.experience = resume.getExperiences();
        this.skills = resume.getSkills();
        this.creationDate = resume.getCreationDate();
        this.updateDate = resume.getUpdateDate();
    }

    public ResumeDTO(String experiences, String education, String skills) {
        this.experience = experiences;
        this.education = education;
        this.skills = skills;
    }
}