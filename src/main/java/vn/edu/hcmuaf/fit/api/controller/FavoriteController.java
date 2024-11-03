package vn.edu.hcmuaf.fit.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.api.dto.FavoriteDTO;
import vn.edu.hcmuaf.fit.api.model.Favorite;
import vn.edu.hcmuaf.fit.api.service.FavoriteService;

import java.util.List;

@RestController
@RequestMapping("api/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    // Create a new Favorite
    @PostMapping()
    public ResponseEntity<Favorite> createFavorite(@RequestBody FavoriteDTO favorite) {
        return new ResponseEntity<>(favoriteService.saveFavorite(favorite), HttpStatus.CREATED);
    }

    // Get all Favorite
    @GetMapping
    public List<Favorite> getAllFavorites() {
        return favoriteService.getFavorites();
    }

    // Get Favorite by id
    @GetMapping("{id}")
    public ResponseEntity<Favorite> getFavoriteById(@PathVariable ("id") int id) {
        return new ResponseEntity<>(favoriteService.getFavoriteByID(id), HttpStatus.OK);
    }

    // Update Favorite by id
    @PutMapping("{id}")
    public ResponseEntity<Favorite> updateFavoriteById(@PathVariable ("id") int id,
                                                       @RequestBody FavoriteDTO favoriteDTO) {
        return new ResponseEntity<>(favoriteService.updateFavoriteByID(id, favoriteDTO), HttpStatus.OK);
    }

    // Delete Favorite by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFavoriteById(@PathVariable ("id") int id) {
        favoriteService.deleteFavoriteByID(id);
        return new ResponseEntity<>("Favorite " + id + " is deleted successfully!", HttpStatus.OK);
    }

}
