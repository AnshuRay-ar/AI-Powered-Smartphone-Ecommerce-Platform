package com.springbootproj.ProjectSpringboot.controller;

import com.springbootproj.ProjectSpringboot.model.Phone;
import com.springbootproj.ProjectSpringboot.services.AiService;
import com.springbootproj.ProjectSpringboot.services.PhoneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.nio.file.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private AiService aiService;

    // ── HOME PAGE ─────────────────────────────────────
    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute(
                "phones",
                phoneService.getAllPhones()
        );

        return "index";
    }

    // ── SHOW ADD PHONE FORM ──────────────────────────
    @GetMapping("/add")
    public String showAddForm(Model model) {

        model.addAttribute(
                "phone",
                new Phone()
        );

        return "add-phone";
    }

    // ── SAVE PHONE WITH DUPLICATE CHECK + IMAGE UPLOAD ──
    @PostMapping("/add")
    public String savePhone(@ModelAttribute Phone phone,
                            @RequestParam("imageFile")
                            MultipartFile imageFile,
                            Model model)
            throws IOException {

        // ── STEP 1: CHECK DUPLICATE RECORD IN DB ─────
        if (phoneService.existsByNameAndBrand(
                phone.getName(), phone.getBrand())) {

            model.addAttribute(
                    "error",
                    "❌ A phone with this name and brand already exists!"
            );
            model.addAttribute("phone", phone);

            return "add-phone";
        }

        // ── STEP 2: HANDLE IMAGE UPLOAD ───────────────
        if (!imageFile.isEmpty()) {

            // Keep original filename EXACTLY as uploaded
            // "IQOO Neo 7.png" stays "IQOO Neo 7.png"
            String fileName =
                    imageFile.getOriginalFilename();

            // Upload folder path
            String uploadDir =
                    System.getProperty("user.dir")
                    + "/src/main/resources/static/images/";

            Path uploadPath = Paths.get(uploadDir);

            // Create folder if it doesn't exist
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path targetPath = uploadPath.resolve(fileName);

            // Only write to disk if file doesn't already exist
            if (!Files.exists(targetPath)) {
                Files.write(targetPath, imageFile.getBytes());
            }

            // Save image URL in DB
            phone.setImageUrl("/images/" + fileName);

        } else {

            // No image uploaded — use default
            phone.setImageUrl("/images/default.png");
        }

        // ── STEP 3: SAVE PHONE RECORD TO DB ──────────
        phoneService.addPhone(phone);

        return "redirect:/";
    }

    // ── DELETE PHONE ─────────────────────────────────
    @PostMapping("/delete/{id}")
    public String deletePhone(@PathVariable int id) {

        phoneService.deletePhone(id);

        return "redirect:/";
    }

    // ── PHONE DETAILS ────────────────────────────────
    @GetMapping("/phone/{id}")
    public String viewPhoneDetails(@PathVariable int id,
                                   Model model) {

        Phone phone =
                phoneService.getPhoneById(id);

        model.addAttribute(
                "phone",
                phone
        );

        return "phone-details";
    }

    // ── ADD TO CART ──────────────────────────────────
@PostMapping("/cart/add/{id}")
public String addToCart(@PathVariable int id,
                        HttpSession session) {

    List<Phone> cart =
            (List<Phone>) session.getAttribute("cart");

    if (cart == null) {

        cart = new ArrayList<>();
    }

    Phone phone =
            phoneService.getPhoneById(id);

    cart.add(phone);

    session.setAttribute("cart", cart);

    // Redirect directly to cart page
    return "redirect:/cart";
}

    // ── VIEW CART ────────────────────────────────────
    @GetMapping("/cart")
    public String viewCart(HttpSession session,
                           Model model) {

        List<Phone> cart =
                (List<Phone>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        double total = 0;

        for (Phone phone : cart) {
            total += phone.getPrice();
        }

        model.addAttribute("cart", cart);
        model.addAttribute("total", total);

        return "cart";
    }

    // ── REMOVE FROM CART ─────────────────────────────
    @PostMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable int id,
                                 HttpSession session) {

        List<Phone> cart =
                (List<Phone>) session.getAttribute("cart");

        if (cart != null) {

            for (int i = 0; i < cart.size(); i++) {

                if (cart.get(i).getId() == id) {

                    cart.remove(i);
                    break;
                }
            }

            session.setAttribute("cart", cart);
        }

        return "redirect:/cart";
    }

    // ── CLEAR CART ───────────────────────────────────
    @PostMapping("/cart/clear")
    public String clearCart(HttpSession session) {

        session.removeAttribute("cart");

        return "redirect:/cart";
    }






    






// ── AI ASK PAGE ──────────────────────────────────
    @GetMapping("/ask")
    public String showAskPage() {

        return "ask";
    }

    // ── HANDLE AI QUESTION ───────────────────────────
    @PostMapping("/ask")
    public String handleAsk(
            @RequestParam String question,
            Model model
    ) {

        // GET PHONES
        List<Phone> phones =
                phoneService.getAllPhones();

        // BUILD PHONE DATA
        StringBuilder phoneData =
                new StringBuilder();

        for (Phone p : phones) {

            phoneData.append(
                    "Phone Name: ")
                    .append(p.getName())

                    .append(", Brand: ")
                    .append(p.getBrand())

                    .append(", Price: ₹")
                    .append(p.getPrice())

                    .append(", Description: ")
                    .append(p.getDescription())

                    .append("\n");
        }

        // SYSTEM PROMPT

String systemPrompt =

        "You are an expert smartphone recommendation assistant for an Indian phone store. "

        + "Your job is to help customers make smart buying decisions using ONLY the phones available in the database below. "

        + "Always give personalized recommendations based on the user's needs such as gaming, battery, camera, performance, display, value for money, or premium experience. "

        + "Explain WHY a phone is a good choice in simple human-friendly language. "

        + "Always answer in a clean point-wise format. "

        + "Use proper headings and bullet points. "

        + "Mention exact phone names and prices and donot use tabular comparision"

        + "If multiple phones are suitable, compare them briefly and clearly. "

        + "Clearly mention which phone is BEST overall and why. "

        + "Never invent phones outside the database. "

        + "Do NOT use markdown symbols like ** or ##. "

        + "Keep responses professional, smart, and easy to understand.\n\n"

        + "AVAILABLE PHONES:\n\n"

        + phoneData.toString();

        // AI RESPONSE
String answer =
        aiService.ask(
                systemPrompt,
                question
        );

// Remove markdown bold symbols
answer = answer.replace("**", "");

        model.addAttribute(
                "question",
                question
        );

        model.addAttribute(
                "answer",
                answer
        );

        return "ask";
    }
}