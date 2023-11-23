package com.example.demo.entity;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FixedExtension")
@Entity
public class FixedExtension {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(unique = true)
    private String extensionName;
    
    private boolean isChecked;

    // isChecked를 Thymeleaf에서 사용할 문자열로 변환
    public String getCheckedValue() {
        return isChecked ? "Y" : "N";
    }

	public Object setCheckedValue(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}