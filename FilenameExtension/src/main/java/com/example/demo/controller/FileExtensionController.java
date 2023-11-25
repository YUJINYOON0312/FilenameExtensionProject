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

import com.example.demo.data.UpdateItemRequest;
import com.example.demo.entity.CustomExtension;
import com.example.demo.service.FileExtensionService;


@Controller
public class FileExtensionController {
	
	@Autowired
	private FileExtensionService service;

	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	//리스트 불러오기
	@GetMapping("/list")
    public String listAll(Model model) {
		//고정
		service.getListAll(model);
		//커스텀
		service.findAll(model);
		return "list";
    }
	
	//커스텀 차단자 등록
	@PostMapping("/list")
    public String customReg(@RequestParam("extensionName") String extensionName, CustomExtension customExtension) {

	    CustomExtension newExtension = new CustomExtension();

	    try {	    	
		    newExtension.setExtensionName(extensionName);
		    service.save(newExtension);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	    return "redirect:/list";
    }
    
	//커스텀 삭제
	@RequestMapping(value = "/list/{cno}", method = RequestMethod.POST)
	public String deleteCustom(@PathVariable Long cno) {
	    service.deleteByCno(cno);
	    return "redirect:/list";
	}
	
	//고정 차단자 수정
    @PostMapping("/updateItem")
    @ResponseBody
    public ResponseEntity<String> updateFixed(@RequestBody UpdateItemRequest request) {
    	service.updateItem(request.getItemId(), request.isChecked());
        return ResponseEntity.ok("Update successful");
    }
	
    
    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<String> updateItems(@RequestParam(name = "checkedItems", required = false) List<String> checkedItems) {
    	service.updateItems(checkedItems);
        return ResponseEntity.ok("Update successful");
    }
	
}
