package com.hyundai.minihompy.controller;

import java.io.File;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hyundai.minihompy.domain.BoardDTO;
import com.hyundai.minihompy.service.BoardService;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@RestController
@RequestMapping("/board")
@Log4j2
public class BoardController {

	@Autowired
	private BoardService boardService;

	// application.properties 변수
	@Value("${com.hyundai.minihompy.upload.path}")
	private String uploadPath;

	// 게시글 작성
	@PostMapping("/insert")
	public BoardDTO insert(BoardDTO boardDTO, Model model) throws Exception {
		log.info(boardDTO.toString());

		try {
			boardDTO.setId("user1");
			boardDTO.setName("김땡땡");
			boardDTO.setPassword("1234");

			// 게시글 내용 개행 치환 처리
			String contents = boardDTO.getContent().replace("\r\n", "<br>");
			boardDTO.setContent(contents);

			// 파일 업로드시 동작
			if (boardDTO.getAttach() != null && !boardDTO.getAttach().isEmpty()) {
				log.info("파일 업로드 시작");
				MultipartFile mf = boardDTO.getAttach();
				boardDTO.setAttachoname(mf.getOriginalFilename());
				boardDTO.setAttachsname(new Date().getTime() + "-" + mf.getOriginalFilename());
				boardDTO.setAttachtype(mf.getContentType());

				try {
					File file = new File(uploadPath + boardDTO.getAttachsname());
					mf.transferTo(file);
				} catch (Exception e) {
					log.info("업로드 에러" + e);
				}
			}
			boardService.insert(boardDTO);
			log.info(boardDTO);
			boardDTO = boardService.getDetail(boardDTO.getBno());
			log.info(boardDTO);
			return boardDTO;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// 게시글 수정
	@PostMapping("/update")
	public BoardDTO update(BoardDTO boardDTO, Model model) throws Exception {
		try {
			boardDTO.setId("user");
			boardDTO.setName("유저1");
			boardDTO.setPassword("1234");

			// 게시글 내용 개행 치환 처리
			String contents = boardDTO.getContent().replace("\r\n", "<br>");
			boardDTO.setContent(contents);

			// 파일 업로드시 동작
			if (boardDTO.getAttach() != null && !boardDTO.getAttach().isEmpty()) {
				MultipartFile mf = boardDTO.getAttach();
				boardDTO.setAttachoname(mf.getOriginalFilename());
				boardDTO.setAttachsname(new Date().getTime() + "-" + mf.getOriginalFilename());
				boardDTO.setAttachtype(mf.getContentType());

				try {
					File file = new File(uploadPath + boardDTO.getAttachsname());
					mf.transferTo(file);
				} catch (Exception e) {
					log.info("업로드 에러" + e);
				}
			}
			boardService.update(boardDTO);
			boardDTO = boardService.getDetail(boardDTO.getBno());
			log.info(boardDTO);
			return boardDTO;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// 게시글 삭제
	@DeleteMapping("/{bno}")
	public Map<String, String> delete(@PathVariable long bno, Model model) throws Exception {
		try {
			boardService.delete(bno);
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "success");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(String fileName) {
		ResponseEntity<byte[]> result = null;
		try {
			String srcFileName = fileName;
			File file = new File(uploadPath + File.separator + srcFileName);
			
			// 썸네일 생성
			String thumnailSaveName = uploadPath + File.separator+ "s_" + srcFileName;
            File thumbailFile = new File(thumnailSaveName);
            //섬네일 파일 생성 100 X 100 생성  input,output, 가로, 세로
            Thumbnailator.createThumbnail(file, thumbailFile, 300,300);
			
			// 헤더 생성 브라우져에 보내야 하므로s
			HttpHeaders headers = new HttpHeaders();
			// 헤더에 콘텐츠 타입 설정
			headers.add("Content-Type", Files.probeContentType(thumbailFile.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(thumbailFile), headers, HttpStatus.OK);
			log.info(result);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} 

		return result;
	}

}
