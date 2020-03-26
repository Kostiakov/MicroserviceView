package com.example.view.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.example.view.entity.BookNew;
import com.example.view.entity.LiteratureList;
import com.example.view.entity.LiteratureNew;
import com.example.view.entity.MagazineNew;

@Controller
public class ViewController {
	
	@GetMapping("/")
	public String showStart() {
		return "start";
	}
	
	@GetMapping("/list")
	public String getLiterature(Model model) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		LiteratureList list = restTemplate.getForObject("http://localhost:8085/bookshop/literature/", LiteratureList.class);
		List <LiteratureNew> literatureAll = list.getList();
		System.out.println(literatureAll);
		
		List <LiteratureNew> listBooks = new ArrayList<>();
		List <LiteratureNew> listMagazines = new ArrayList<>();
		
		/*BookNew literature1 = new BookNew();
		literature1.setAuthor("qwer");
		literature1.setPublisher("asdf");
		literature1.setTitle("zxcv");
		literature1.setYear(1234);
		BookNew literature2 = new BookNew();
		literature2.setAuthor("utyhn");
		literature2.setPublisher("dfbdf");
		literature2.setTitle("mkr");
		literature2.setYear(1234);
		MagazineNew literature3 = new MagazineNew();
		literature3.setPublisher("hgfd");
		literature3.setTitle("zxbvc");
		literature3.setYear(2000);
		literature3.setNumbersPerYear(6);
		
		List <LiteratureNew> literatureAll = new ArrayList<>();
		List <LiteratureNew> listBooks = new ArrayList<>();
		List <LiteratureNew> listMagazines = new ArrayList<>();
		literatureAll.add(literature1);
		literatureAll.add(literature2);
		literatureAll.add(literature3);*/
		Iterator iterator = literatureAll.iterator();
		while (iterator.hasNext()) { 
			Object obj = iterator.next();
            if(obj instanceof BookNew) {
            	listBooks.add((BookNew)obj);
            	continue;
            }
            if(obj instanceof MagazineNew) {
            	listMagazines.add((MagazineNew)obj);
            }
        } 
		
		model.addAttribute("books", listBooks);
		model.addAttribute("magazines", listMagazines);
		return "list";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		BookNew theBook=new BookNew();
		model.addAttribute("book", theBook);
		MagazineNew theMagazine=new MagazineNew();
		model.addAttribute("magazine", theMagazine);
		return "literature-form";
	}
	
	@PostMapping("/addBook")
	public String addBook(@ModelAttribute("book") BookNew theBook) {
		RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<?> entity = new HttpEntity<Object>(theBook,headers);
	    ResponseEntity<LiteratureNew> responseEntity = restTemplate.exchange("http://localhost:8085/bookshop/literature/book", HttpMethod.POST, entity, LiteratureNew.class);
		return "redirect:/";
	}
	
	@PostMapping("/addMagazine")
	public String addMagazine(@ModelAttribute("magazine") MagazineNew theMagazine) {
		RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<?> entity = new HttpEntity<Object>(theMagazine,headers);
	    ResponseEntity<LiteratureNew> responseEntity = restTemplate.exchange("http://localhost:8085/bookshop/literature/magazine", HttpMethod.POST, entity, LiteratureNew.class);
		return "redirect:/";
	}
	
	@RequestMapping("/delete")
	public String deleteLiterature(@RequestParam("literatureId") int theId, Model model) {
		RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<?> entity = new HttpEntity<Object>(theId,headers);
	    ResponseEntity<Integer> responseEntity = restTemplate.exchange("http://localhost:8085/bookshop/literature/" + theId, HttpMethod.DELETE, entity, Integer.class);
		return "redirect:/";
	}

}
