import abc


class Funcionario(abc.ABC):

    @abc.abstractmethod
    def get_bonificacao(self):
        pass


# interface
class Autenticavel(abc.ABCMeta):

    @abc.abstractmethod
    def autentica(self, senha):
        """ Método abstrato que faz verificação da senha
        return True se a senha confere, e False caso contrário.
        """


if __name__ == '__main__':
    f = Funcionario()
    Autenticavel.register(f)
