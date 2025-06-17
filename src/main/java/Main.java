import br.com.dio.dao.UserDao;
import br.com.dio.exception.EmptyStorageException;
import br.com.dio.exception.UserNotFoundException;
import br.com.dio.model.MenuOption;
import br.com.dio.model.UserModel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private final static UserDao dao = new UserDao();
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true){
            System.out.println("Bem vindo ao cadastro de usuarios, selecione a operação desejada: ");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - atualizar");
            System.out.println("3 - excluir");
            System.out.println("4 - Buscar por id");
            System.out.println("5 - Listar");
            System.out.println("6 - Sair");
            var userInput = scanner.nextInt();
            var selectedOption = MenuOption.values()[userInput - 1];
            switch (selectedOption){
                case SAVE -> {
                    var user = dao.save(requestToSave());
                    System.out.printf("Usuario salvo com sucesso %s", user);
                }
                case UPDATE -> {
                    try {
                        var user = dao.update(requestToUpdate());
                        System.out.printf("Usuario atualizado com sucesso %s", user);
                    }catch (UserNotFoundException | EmptyStorageException ex){
                        System.out.println(ex.getMessage());
                    }

                }
                case DELETE -> {
                    try {
                        dao.delete(requestId());
                        System.out.println("Usuario excluido com sucesso");
                    }catch (UserNotFoundException | EmptyStorageException ex){
                        System.out.println(ex.getMessage());
                    }

                }
                case FIND_BY_ID ->{
                    try {
                        var id = requestId();
                        var user = dao.findById(id);
                        System.out.printf("Usuario id %s: ",id);
                        System.out.println(user);
                    }catch (UserNotFoundException | EmptyStorageException ex){
                        System.out.println(ex.getMessage());
                    }

                }
                case LIST_ALL -> {
                    var users = dao.findAll();
                    System.out.println("Usuarios cadastrados: ");
                    users.forEach(System.out::println);
                }
                case EXIT -> System.exit(0);


            }
        }
    }

    private static int requestId(){
        System.out.println("Informe o identificador do usuario: ");
        return scanner.nextInt();
    }
    private static UserModel requestToSave(){
        System.out.println("Informe o nome do usuario: ");
        var name = scanner.next();
        System.out.println("Informe o email do usuario: ");
        var email = scanner.next();
        System.out.println("Informe o Data de nascimento do usuario: ");
        var birthdayString = scanner.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var birthday = LocalDate.parse((birthdayString), formatter);
        var user = new UserModel(0, name, email,birthday);
        return user;
    }
    private static UserModel requestToUpdate(){
        System.out.println("Informe o nome do usuario: ");
        var name = scanner.next();
        System.out.println("Informe o identificador do usuario: ");
        var id = scanner.nextInt();
        System.out.println("Informe o email do usuario: ");
        var email = scanner.next();
        System.out.println("Informe o Data de nascimento do usuario: ");
        var birthdayString = scanner.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var birthday = LocalDate.parse((birthdayString), formatter);
        var user = new UserModel(0, name, email,birthday);
        return user;
    }

}
