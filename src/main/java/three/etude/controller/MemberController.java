package three.etude.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import three.etude.domain.Member;
import three.etude.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String goMain() {
        //System.out.println("Controller : createForm");
        return "main";
    }

    @GetMapping("/join")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/join")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setM_id(form.getM_id());
        member.setM_pwd(form.getM_pwd());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "members/loginForm";
    }

    @PostMapping("/login")
    public String login(MemberForm form, HttpSession session) throws Exception {

        Member member = new Member();
        member.setM_id(form.getM_id());
        member.setM_pwd(form.getM_pwd());

//        HttpSession session = req.getSession();
        Member login = memberService.login(member);

        if (login != null) {
            session.setAttribute("member", login);

            return "redirect:/";

        } else {
            return "members/loginForm";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) throws Exception {
        session.invalidate();

        return "redirect:/";
    }
}
