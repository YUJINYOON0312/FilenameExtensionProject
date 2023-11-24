package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.CustomExtension;
import com.example.demo.entity.UpdateItemRequest;
import com.example.demo.service.FileExtensionService;


@Controller
public class FileExtensionController {
	
	@Autowired
	private FileExtensionService service;


	@GetMapping("/")
	public String index() {
		return "/index";
	}
	
	@GetMapping("/list")
    public String ListReg(Model model) {
		//고정
		service.getListAll(model);
		//커스텀
		service.findAll(model);
		return "/list";
    }
	
	//산규 차단자 등록
	@PostMapping("/list")
    public String FixedList(@RequestParam("extensionName") String extensionName, CustomExtension customExtension) {
	    // CustomExtension 객체 생성 및 설정
	    CustomExtension newExtension = new CustomExtension();

	    try {
	    	
	    newExtension.setExtensionName(extensionName);
	    service.save(newExtension);
	    
	    }catch(Exception e){
	    	
	    	e.printStackTrace();
	    }
	    
	    return "redirect:/list";
    }
	
	
	//고정 차단자 수정
    @PostMapping("/updateItem")
    @ResponseBody
    public ResponseEntity<String> updateItem(@RequestBody UpdateItemRequest request) {
    	service.updateItem(request.getItemId(), request.isChecked());
        return ResponseEntity.ok("성공!!!!!!!!!!!");
    }
    
    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<String> updateItems(@RequestParam(name = "checkedItems", required = false) List<String> checkedItems) {
    	service.updateItems(checkedItems);
        return ResponseEntity.ok("Update successful");
    }
	
	
	@GetMapping("/custom-extension")
    public void CustomList(@RequestParam Long no, @RequestParam boolean isChecked) {
        
    }
	
	
	
	
	//수정
	@PostMapping("/custom-extension")
    public void updateCustom(@RequestParam Long no, @RequestParam boolean isChecked) {
        
    }
	
	// 커스텀 삭제
	@RequestMapping(value = "/list/{cno}", method = RequestMethod.POST)
	public String deleteCustomExtension(@PathVariable Long cno) {
		System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz"+cno);
	    service.deleteByCno(cno);
	    System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz"+cno);
	    return "redirect:/list";
	}
}
