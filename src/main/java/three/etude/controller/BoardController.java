package three.etude.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import three.etude.domain.Board;
import three.etude.domain.Good;
import three.etude.domain.Member;
import three.etude.domain.Reply;
import three.etude.service.BoardService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/boardList")
    public String getList(Model model,
                          @RequestParam(required=false) String search,
                          @RequestParam(required=false) String keyword) {

        if (search == null)    search = "";
        if (keyword == null)   keyword = "";

        List<Board> boardList = boardService.getList(search, keyword);

        model.addAttribute("boardList", boardList);
        model.addAttribute("search", search);
        model.addAttribute("keyword", keyword);

        return "board/boardList";
    }

    @GetMapping("/boardDetail")
    public String getDetail(Model model,
                            @RequestParam int idx,
                            @RequestParam String wtype,
                            HttpSession session) {
        Board board = boardService.getDetail(idx);
        List<Reply> replyList = boardService.getReplys(idx);

        Member member = (Member)session.getAttribute("member");
        String mid = "";
        if (member != null) mid = member.getM_id();

        Good good = boardService.getGood(idx, mid);

        model.addAttribute("board", board);
        model.addAttribute("replyList", replyList);
        model.addAttribute("good", good);

        if (wtype.equals("v")) return "board/boardDetail";
        else return "board/updateForm";
    }

    @GetMapping("/getForm")
    public String getForm() {
        return "board/registerForm";
    }

    @PostMapping("/register")
    public String register(BoardForm form) {
        int idx = boardService.register(form);

        return "redirect:/boardDetail?wtype=v&idx=" + idx;
    }

    @PostMapping("/update")
    public String doUpdate(BoardForm form, Model model) {
        boardService.doUpdate(form);

        return "redirect:/boardDetail?wtype=v&idx=" + form.getB_idx();
    }

    @GetMapping("delete")
    public String doDelete(@RequestParam int idx) {

        boardService.doDelete(idx);

        return "redirect:/boardList";
    }

    @PostMapping("/regReply")
    public String regReply(@RequestParam int b_idx,
                           @RequestParam String m_id,
                           @RequestParam String r_content) {
        Reply reply = new Reply();

        reply.setM_id(m_id);
        reply.setB_idx(b_idx);
        reply.setR_content(r_content);

        boardService.regReply(reply);

        return "redirect:/boardDetail?wtype=v&idx=" + b_idx;
    }

    @GetMapping("/delReply")
    public String delReply(@RequestParam int r_idx, @RequestParam int b_idx) {
        boardService.delReply(r_idx, b_idx);

        return "redirect:/boardDetail?wtype=v&idx=" + b_idx;
    }

    @PostMapping("/likeOrUnlike")
    public void likeOrUnlike(@RequestParam String x,
                             @RequestParam int idx,
                             HttpServletResponse response,
                             HttpSession session) {
        int result = 0;

        Member member = (Member) session.getAttribute("member");

        if (member != null) {
            result = boardService.likeOrUnlike(x, idx, member.getM_id());
        } else result = 5;

        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println(result);
        out.close();
    }
}
