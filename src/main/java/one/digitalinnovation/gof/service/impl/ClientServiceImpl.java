package one.digitalinnovation.gof.service.impl;

import one.digitalinnovation.gof.model.Address;
import one.digitalinnovation.gof.model.Client;
import one.digitalinnovation.gof.repository.AddressRepository;
import one.digitalinnovation.gof.repository.ClientRepository;
import one.digitalinnovation.gof.service.ClientService;
import one.digitalinnovation.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementação da <b>Strategy</b> {@link ClientService}, a qual
 * pode ser injetada pelo Spring (via{@link Autowired}).
 * Com isso, como essa classe {@link Service},
 * ela será tratada como um <b>Singleton</b>.
 */

@Service
public class ClientServiceImpl implements ClientService {

    //Singleton: Injetar os componentes do Spring com @Autowired
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ViaCepService viaCepService;

    //Strategy: Implementar os metodos definidos na interface.
    //Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

    @Override
    public Iterable<Client> buscarTodos() {
        return clientRepository.findAll();
    }

    @Override
    public Client buscarPorId(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.get();
    }

    @Override
    public void inserir(Client client) {
        saveClientWithCep(client);
    }

    private void saveClientWithCep(Client client) {
        // Verificar se o Endereço do Cliente já existe (pelo CEP)
        String cep = client.getAddress().getCep();
        Address address = addressRepository.findById(cep).orElseGet(() -> {

            //CAso não exista, integrar com o ViaCep e persistir o retorno.
            Address newAddress = viaCepService.consultarCep(cep);
            addressRepository.save(newAddress);
            return newAddress;
        });
        client.setAddress(address);
        // Inserir Cliente, vinculando o Endereço (novo ou existente)
        clientRepository.save(client);
    }

    @Override
    public void atualizar(Long id, Client client) {
        // Buscar Cliente por ID, caso exista.
        Optional<Client> clientBD = clientRepository.findById(id);
        if (clientBD.isPresent()) {
            saveClientWithCep(client);
        }
    }

    @Override
    public void deletar(Long id) {
        // Deletar cliente por ID
        clientRepository.deleteById(id);
    }
}
