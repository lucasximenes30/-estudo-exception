package br.com.dio.dao;

import br.com.dio.exception.UserNotFoundException;
import br.com.dio.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Long nextId = 1L;

    private final List <UserModel> models = new ArrayList<>();

    public UserModel save(final UserModel model) {
        model.setId(nextId++);
        models.add(model);
        return model;
    }
    public UserModel update(final UserModel model){
        var toUpdate = findById(model.getId());
        models.remove(toUpdate);
        models.add(model);
        return model;
    }
    public UserModel findById(final Long id){
        var message = String.format("Não existe usuario com o id %d");
        return models.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(message));

    }
}
