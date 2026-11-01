package com.example.gamezone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.gamezone.entity.Admin;
import com.example.gamezone.entity.GameScore;
import com.example.gamezone.entity.User;
import com.example.gamezone.repository.UserRepository;
import com.example.gamezone.service.AdminService;
import com.example.gamezone.service.GameScoreService;


import jakarta.servlet.http.HttpSession;
import com.example.gamezone.entity.Game;
import com.example.gamezone.service.GameService;
import org.springframework.web.bind.annotation.PathVariable;
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameScoreService gameScoreService;
    @Autowired
    private GameService gameService;

    @GetMapping("/admin-login")
    public String adminLoginPage() {
        return "admin-login";
    }

    @PostMapping("/admin-login")
    public String adminLogin(
            String username,
            String password,
            Model model,
            HttpSession session) {

        Admin admin = adminService.loginAdmin(username, password);

        if (admin != null) {
            session.setAttribute("adminUsername", admin.getUsername());
            return "redirect:/admin-dashboard";
        }

        model.addAttribute("error",
                "Invalid Admin Username or Password!");

        return "admin-login";
    }

    @GetMapping("/admin-dashboard")
    public String adminDashboard(
            HttpSession session,
            Model model) {

        String adminUsername =
                (String) session.getAttribute("adminUsername");

        if (adminUsername == null) {
            return "redirect:/admin-login";
        }

        long totalUsers = userRepository.count();
        long totalScores = gameScoreService.getAllScores().size();

        model.addAttribute("adminUsername", adminUsername);
        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("totalScores", totalScores);
        long totalGames = gameService.getAllGames().size();

        model.addAttribute("totalGames", totalGames);

        return "admin-dashboard";
    }
    
    @GetMapping("/admin-users")
    public String adminUsers(
            HttpSession session,
            Model model) {

        String adminUsername =
                (String) session.getAttribute("adminUsername");

        if (adminUsername == null) {
            return "redirect:/admin-login";
        }

        List<User> users = userRepository.findAll();

        model.addAttribute("users", users);

        return "admin-users";
    }
   
    @GetMapping("/admin-scores")
    public String adminScores(
            HttpSession session,
            Model model) {

        String adminUsername =
                (String) session.getAttribute("adminUsername");

        if (adminUsername == null) {
            return "redirect:/admin-login";
        }

        List<GameScore> scores = gameScoreService.getAllScores();

        model.addAttribute("scores", scores);

        return "admin-scores";
    }
    @GetMapping("/admin-logout")
    public String adminLogout(HttpSession session) {

        session.removeAttribute("adminUsername");

        return "redirect:/admin-login";
    }
    @GetMapping("/admin-games")
    public String adminGames(HttpSession session, Model model) {

        String adminUsername =
                (String) session.getAttribute("adminUsername");

        if (adminUsername == null) {
            return "redirect:/admin-login";
        }

        List<Game> games = gameService.getAllGames();

        model.addAttribute("games", games);

        return "admin-games";
    }
    @PostMapping("/admin-games/add")
    public String addGame(Game game, HttpSession session) {

        String adminUsername =
                (String) session.getAttribute("adminUsername");

        if (adminUsername == null) {
            return "redirect:/admin-login";
        }

        gameService.saveGame(game);

        return "redirect:/admin-games";
    }
    @GetMapping("/admin-games/delete/{id}")
    public String deleteGame(
            @PathVariable Long id,
            HttpSession session) {

        String adminUsername =
                (String) session.getAttribute("adminUsername");

        if (adminUsername == null) {
            return "redirect:/admin-login";
        }

        gameService.deleteGame(id);

        return "redirect:/admin-games";
    }
    @GetMapping("/admin-games/edit/{id}")
    public String editGame(
    		@PathVariable Long id,
            HttpSession session,
            Model model) {

        String adminUsername =
                (String) session.getAttribute("adminUsername");

        if (adminUsername == null) {
            return "redirect:/admin-login";
        }

        Game game = gameService.getGameById(id);

        model.addAttribute("game", game);

        return "edit-game";
    }
    @PostMapping("/admin-games/update")
    public String updateGame(
            Game game,
            HttpSession session) {

        String adminUsername =
                (String) session.getAttribute("adminUsername");

        if (adminUsername == null) {
            return "redirect:/admin-login";
        }

        gameService.saveGame(game);

        return "redirect:/admin-games";
    }
}