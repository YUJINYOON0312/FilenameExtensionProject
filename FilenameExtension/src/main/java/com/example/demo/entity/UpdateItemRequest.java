package com.example.demo.entity;

import lombok.Data;

@Data
public class UpdateItemRequest {
	  private Long itemId;
	  private boolean checked;
}
