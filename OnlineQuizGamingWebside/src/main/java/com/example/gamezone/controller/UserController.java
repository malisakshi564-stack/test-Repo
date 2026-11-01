
package com.example.gamezone.controller;

import com.example.gamezone.entity.User;
import com.example.gamezone.entity.Game;
import com.example.gamezone.service.UserService;
import com.example.gamezone.service.GameService;
import com.example.gamezone.service.PaymentService;
import com.example.gamezone.service.BadgeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GameService gameService;

    @Autowired
    private BadgeService badgeService;

    // PaymentService Spring कडून inject होईल
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(User user, Model model) {

        if (user.getPassword() == null
                || user.getPassword().length() < 6) {

            model.addAttribute(
                "error",
                "Password must be at least 6 characters!"
            );

            return "register";
        }

        User existingUser = userService.registerUser(user);

        if (existingUser == null) {
            model.addAttribute(
                "error",
                "Email already registered!"
            );

            return "register";
        }

        badgeService.saveBadge(
            new com.example.gamezone.entity.Badge(
                user.getUsername(),
                "First Player",
                "Welcome to GameZone! You earned your first achievement."
            )
        );

        model.addAttribute(
            "message",
            "Registration Successful!"
        );

        return "login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            User user,
            Model model,
            HttpSession session) {

        User loggedUser = userService.loginUser(
            user.getEmail(),
            user.getPassword()
        );

        if (loggedUser != null) {

            session.setAttribute(
                "username",
                loggedUser.getUsername()
            );

            session.setAttribute(
                "email",
                loggedUser.getEmail()
            );

            return "redirect:/home";
        }

        model.addAttribute(
            "error",
            "Invalid Email or Password!"
        );

        return "login";
    }

    @GetMapping("/home")
    public String homePage(
            HttpSession session,
            Model model) {

        String username =
            (String) session.getAttribute("username");

        if (username == null) {
            return "redirect:/login";
        }

        List<Game> games = gameService.getAllGames();

        model.addAttribute("username", username);
        model.addAttribute("games", games);

        return "home";
    }

    // FREE GAME
    @GetMapping("/tic-tac-toe")
    public String ticTacToe(
            Model model,
            HttpSession session) {

        String username =
            (String) session.getAttribute("username");

        if (username == null) {
            return "redirect:/login";
        }

        model.addAttribute("username", username);

        return "tic-tac-toe";
    }

    // PAID GAME: QUIZ
    @GetMapping("/quiz")
    public String quiz(
            Model model,
            HttpSession session) {

        String username =
            (String) session.getAttribute("username");

        String email =
            (String) session.getAttribute("email");

        if (username == null || email == null) {
            return "redirect:/login";
        }

        // Database मधील PAID payment तपासा
        if (!paymentService.hasPaidForGame(email, "Quiz")) {
            return "redirect:/payments/demo?gameName=Quiz";
        }

        model.addAttribute("username", username);

        return "quiz";
    }

    // PAID GAME: ROCK PAPER SCISSORS
    @GetMapping("/rock-paper-scissors")
    public String rockPaperScissors(Model model, HttpSession session) {

        String username = (String) session.getAttribute("username");
        String email = (String) session.getAttribute("email");

        if (username == null || email == null) {
            return "redirect:/login";
        }

        // Check demo payment from database
        if (!paymentService.hasPaidForGame(email, "Rock Paper Scissors")) {
            return "redirect:/payments/demo?gameName=Rock%20Paper%20Scissors";
        }

        model.addAttribute("username", username);
        return "rock-paper-scissors";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }
}
