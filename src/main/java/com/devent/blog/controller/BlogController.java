package com.devent.blog.controller;

import com.devent.blog.model.Post;
import com.devent.blog.repository.PostRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogController {
    private final PostRepository postRepository;

    // Inyección de dependencias por constructor
    public BlogController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // Página principal: lista de posts, del más reciente al más antiguo
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postRepository.findAllByOrderByCreateTimeDesc());
        return "index";
    }

    // Formulario para crear un post nuevo
    @GetMapping("/nuevo")
    public String showForm(Model model) {
        model.addAttribute("post", new Post());
        return "formulario";
    }

    // Guarda un post nuevo o actualiza uno existente
    @PostMapping("/guardar")
    public String savePost(@Valid @ModelAttribute("post") Post post, BindingResult result) {

        // Si la validación falla (ej. título vacío), volvemos a mostrar el formulario con los errores
        if (result.hasErrors()) {
            return "formulario";
        }

        if (post.getId() == null) {
            postRepository.save(post);
            return "redirect:/";
        }

        // Al editar, solo actualizamos los campos del formulario para no perder la fecha de creación
        Post existente = postRepository.findById(post.getId()).orElse(null);
        if (existente == null) {
            return "redirect:/";
        }
        existente.setTitle(post.getTitle());
        existente.setContent(post.getContent());
        postRepository.save(existente);
        return "redirect:/post/" + existente.getId();
    }

    // Formulario de edición con los datos del post
    @GetMapping("/editar/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            return "redirect:/";
        }

        model.addAttribute("post", post);
        return "formulario";
    }

    // Elimina un post (POST para que un simple enlace o un rastreador no borre datos)
    @PostMapping("/eliminar/{id}")
    public String eliminarPost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return "redirect:/";
    }

    // Vista de detalle de un post
    @GetMapping("/post/{id}")
    public String verPost(@PathVariable Long id, Model model) {
        // Si no lo encuentra, redirige al inicio en lugar de romperse
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            return "redirect:/";
        }

        model.addAttribute("post", post);
        return "detalle";
    }
}
