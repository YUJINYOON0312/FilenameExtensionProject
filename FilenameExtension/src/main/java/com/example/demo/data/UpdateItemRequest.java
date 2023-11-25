package com.example.demo.data;

import lombok.Data;

@Data
public class UpdateItemRequest {
	  private Long itemId;
	  private boolean checked;
}
