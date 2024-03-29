package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    public void write(Board board, MultipartFile file) throws Exception{

        String projectPath=System.getProperty("user.dir")+"\\src\\main\\resource\\static\\files";
        UUID uuid= UUID.randomUUID();
        String fileName=uuid+"-"+file.getOriginalFilename();
        File saveFile=new File(projectPath,fileName);
        file.transferTo(saveFile);
        boardRepository.save(board);
    } //글 작성 처리
    public List<Board> boardList(){
        return boardRepository.findAll();
    } //게시글 리스트 처리
    public Board boardView(Integer id){
        return boardRepository.findById(id).get();
    } //특정 게시글 불러오기
    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }
}
