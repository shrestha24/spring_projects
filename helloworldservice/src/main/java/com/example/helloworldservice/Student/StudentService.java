package com.example.helloworldservice.Student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public void deleteStudent(Long id) {
	}
}
