package com.example.helloworldservice.Student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.joran.conditional.IfAction;
import jakarta.transaction.Transactional;

@Service
public class StudentService {

private static StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
	StudentService.studentRepository = studentRepository;
}

	public static List<Student> getStudents(){
		return studentRepository.findAll();
			
	}

    public void addNewStudent(Student student) {
		Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

		if(studentByEmail.isPresent()){
			throw new IllegalStateException("email already present");
		}
		
		studentRepository.save(student);
    }

	public void deleteStudent(Long studentid) {
		boolean exists = studentRepository.existsById(studentid);
		if(!exists){
			throw new IllegalStateException("student with id" +studentid +"does not exists");
		}
		studentRepository.deleteById(studentid);
	}

	@Transactional
	public void updateStudent(Long studentId, String name, String email) {

		Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(
			"student with id" + studentId + "does not exist"));

			if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
				student.setName(name);
			}

			if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
				Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

				if(studentOptional.isPresent()) {
					throw new IllegalStateException("email already present");
				}
				student.setEmail(email);
			}
	}
}
