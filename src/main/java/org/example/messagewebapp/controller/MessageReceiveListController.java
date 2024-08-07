package org.example.messagewebapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.messagewebapp.common.CookieUtil;
import org.example.messagewebapp.dao.MessageDAO;
import org.example.messagewebapp.dao.RoomDAO;
import org.example.messagewebapp.dao.UserDAO;
import org.example.messagewebapp.vo.MessageVO;
import org.example.messagewebapp.vo.UserVO;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/message/receivelist")
@Log4j2
public class MessageReceiveListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie userCookie = CookieUtil.getCookie(req, "user_id");
        if (userCookie == null) {
            resp.sendRedirect("/login");
        }
        String user_id = userCookie.getValue();
        try {
            Optional<UserVO> userOpt = UserDAO.INSTANCE.getUserById(user_id);
            if (userOpt.isEmpty()) {
                resp.sendRedirect("/login");
            }
            UserVO user = userOpt.get();
            req.setAttribute("user", user);
            String room_name = RoomDAO.INSTANCE.getRoomNameByNo(user.getRoom_no());
            req.setAttribute("room_name", room_name);
            List<MessageVO> receive_messages = MessageDAO.INSTANCE.getReceiveMessage(user_id);
            req.setAttribute("receive_messages", receive_messages);
            req.getRequestDispatcher("/WEB-INF/message/receivelist.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
