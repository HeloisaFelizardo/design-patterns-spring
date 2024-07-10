package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Client;

/**
 * Interface que define o padrão <b>Strategy</b> no dominio de c
 * cliente. Com isso, se necessário, podemos ter multiplas
 * implementações dessa mesma interface.
 */

public interface ClientService {

    Iterable<Client> buscarTodos();

    Client buscarPorId(Long id);

    void inserir(Client client);

    void atualizar(Long id, Client client);

    void deletar(Long id);
}
