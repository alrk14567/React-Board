package com.nc13.react_board.model;

import lombok.Data;

import java.util.Date;

@Data
public class BoardDTO {
    private int id;
    private String title;
    private String content;
    private int writerId;
    private String nickname;
    private Date entryDate;
    private Date modifyDate;
}
