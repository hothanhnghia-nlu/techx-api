package vn.edu.hcmuaf.fit.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.api.dto.FavoriteDTO;
import vn.edu.hcmuaf.fit.api.model.Favorite;
import vn.edu.hcmuaf.fit.api.service.FavoriteService;

import java.util.List;

@RestController
@RequestMapping("api/v1/favorites")
@Tag(name = "Favorite Controller")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    // Create a new Favorite
    @PostMapping()
    public ResponseEntity<Favorite> createFavorite(@RequestParam int productId,
                                                   @ModelAttribute FavoriteDTO favorite) {
        return new ResponseEntity<>(favoriteService.saveFavorite(productId, favorite), HttpStatus.CREATED);
    }

    // Get all Favorite
    @GetMapping(path = "/all-favorites")
    public List<FavoriteDTO> getAllFavorites() {
        return favoriteService.getAllFavorites();
    }

    // Get Favorite by user
    @GetMapping(path = "/by-user")
    public ResponseEntity<List<FavoriteDTO>> getFavoritesByUser() {
        return new ResponseEntity<>(favoriteService.getFavoriteByUser(), HttpStatus.OK);
    }

    // Get Favorite by id
    @GetMapping("{id}")
    public ResponseEntity<Favorite> getFavoriteById(@PathVariable ("id") int id) {
        return new ResponseEntity<>(favoriteService.getFavoriteByID(id), HttpStatus.OK);
    }

    // Delete Favorite by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFavoriteById(@PathVariable ("id") int id) {
        favoriteService.deleteFavoriteByID(id);
        return new ResponseEntity<>("Favorite " + id + " is deleted successfully!", HttpStatus.OK);
    }

    // Delete Favorite by product id
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteFavoriteByProductId(@PathVariable ("productId") int productId) {
        favoriteService.deleteFavoriteByProduct(productId);
        return new ResponseEntity<>("Favorite by product id "
                + productId + " is deleted successfully!", HttpStatus.OK);
    }

}
