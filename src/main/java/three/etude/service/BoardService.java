package three.etude.service;

import three.etude.controller.BoardForm;
import three.etude.domain.Board;
import three.etude.domain.Good;
import three.etude.domain.Reply;
import three.etude.repository.BoardRepository;

import java.util.List;

public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService (BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getList(String search, String keyword) {
        return boardRepository.getList(search, keyword);
    }

    public Board getDetail(int idx) {
        return boardRepository.getDetail(idx);
    }

    public Good getGood(int idx, String mid) {
        return boardRepository.getGood(idx, mid);
    }

    public List<Reply> getReplys(int idx) {
        return boardRepository.getReplys(idx);
    }

    public int regReply(Reply reply) {
        return boardRepository.regReply(reply);
    }

    public int delReply(int r_idx, int b_idx) {
        return boardRepository.delReply(r_idx, b_idx);
    }

    public int register(BoardForm form) {
        return boardRepository.register(form);
    }

    public int doUpdate(BoardForm form) {
        return boardRepository.doUpdate(form);
    }

    public void doDelete(int idx) {
        boardRepository.doDelete(idx);
    }

    public int likeOrUnlike(String x, int idx, String mid) {
        return boardRepository.likeOrUnlike(x, idx, mid);
    }
}
