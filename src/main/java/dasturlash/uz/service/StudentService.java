package dasturlash.uz.service;

import dasturlash.uz.dto.StudentDTO;
import dasturlash.uz.entity.StudentEntity;
import dasturlash.uz.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO create(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());

        studentRepository.save(entity);
        dto.setId(entity.getId());

        return dto;
    }

    public List<StudentDTO> getAllStudent() {
        Iterable<StudentEntity> entityList = studentRepository.findAll();

        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public StudentDTO getById(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        StudentEntity entity = optional.get();

        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setAge(entity.getAge());

        return dto;
    }

    public Boolean update(StudentDTO dto, Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            return false;
        }
        StudentEntity entity = optional.get();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        studentRepository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }

    public PageImpl<StudentDTO> pagination(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<StudentEntity> result = studentRepository.findAll(pageRequest);

        long totalCount = result.getTotalElements();
        List<StudentEntity> entityList = result.getContent();

        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dtoList.add(dto);
        }
        return new PageImpl<StudentDTO>(dtoList, pageRequest, totalCount);
    }

    public PageImpl<StudentDTO> paginationWithName(String name, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<StudentEntity> result = studentRepository.findByName(name, pageRequest);

        long totalCount = result.getTotalElements();
        List<StudentEntity> entityList = result.getContent();

        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dtoList.add(dto);
        }
        return new PageImpl<StudentDTO>(dtoList, pageRequest, totalCount);
    }

    public PageImpl<StudentDTO> paginationWithNameAndSurname(String name, String surname, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<StudentEntity> result = studentRepository.findByNameAndSurname(name, surname, pageRequest);

        long totalCount = result.getTotalElements();
        List<StudentEntity> entityList = result.getContent();

        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dtoList.add(dto);
        }
        return new PageImpl<StudentDTO>(dtoList, pageRequest, totalCount);
    }

    public PageImpl<StudentDTO> paginationWithQuery(String name, int age, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<StudentEntity> result = studentRepository.findAllByNameAndAge(name, age, pageRequest);

        long totalCount = result.getTotalElements();
        List<StudentEntity> entityList = result.getContent();

        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dtoList.add(dto);
        }
        return new PageImpl<StudentDTO>(dtoList, pageRequest, totalCount);
    }

}
