package com.student.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DoubtReplyDto {

	private Long doubtId;
    private String reply;
    private Long teacherId;
}
