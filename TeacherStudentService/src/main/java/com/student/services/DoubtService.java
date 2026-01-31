package com.student.services;

import java.util.List;

import com.student.dtos.ApiResponse;
import com.student.dtos.DoubtDto;
import com.student.entities.Doubt;

public interface DoubtService {

	ApiResponse askDoubt(DoubtDto dto);

    ApiResponse replyToDoubt(Long doubtId, String reply);

    List<DoubtDto> getStudentDoubts(Long studentId);

    List<Doubt> getTeacherDoubts(Long teacherId);
}
