package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.example.demo.entity.CustomExtension;
import com.example.demo.entity.CustomExtensionRepo;
import com.example.demo.entity.FixedExtension;
import com.example.demo.entity.FixedExtensionRepo;

@Service
public class FileExtensionService {

    @Autowired
    private FixedExtensionRepo fixedExtensionRepo;

    @Autowired
    private CustomExtensionRepo customExtensionRepo;

    public void getListAll(Model model) {
        List<FixedExtension> result = fixedExtensionRepo.findAll();

        model.addAttribute("list", result);
    }

    public void updateItems(List<String> selectedItems) {
        for (String selectedItem : selectedItems) {
            Optional<FixedExtension> optionalItem = fixedExtensionRepo.findById(Long.parseLong(selectedItem));
            optionalItem.ifPresent(item -> {
                item.setChecked(!item.isChecked()); 
                fixedExtensionRepo.save(item);
            });
        }
    }

    public void updateItem(Long itemId, boolean checked) {
        // 해당 번호의 아이템을 찾아 isChecked 값을 업데이트
        Optional<FixedExtension> optionalItem = fixedExtensionRepo.findById(itemId);
        optionalItem.ifPresent(item -> {
            item.setChecked(checked); // isChecked를 체크박스값으로 업데이트
            fixedExtensionRepo.save(item);
        });
    }


	
	//////////////////////////////////////

	
	
	// 커스텀 등록
	@Transactional
	public void save(CustomExtension customExtension) {

		customExtensionRepo.save(customExtension);
	}

	// 커스텀 리스트 불러오기
	@Transactional
	public void findAll(Model model) {

		List<CustomExtension> result = customExtensionRepo.findAll();
		model.addAttribute("customList", result);

	}

	@Transactional
	public void delete(long cno) {
		customExtensionRepo.deleteById(cno);
	}

}

