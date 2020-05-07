class Conta:

    _total_contas = 0
    __slots__ = ['_id', '_saldo']

    def __init__(self, saldo=0.0):
        self._id = Conta._total_contas
        self.saldo = saldo
        Conta._total_contas += 1

    @property
    def saldo(self):
        return self._saldo

    @saldo.setter
    def saldo(self, saldo):
        if saldo < 0:
            print("saldo não pode ser negativo")
        else:
            self._saldo = saldo

    @staticmethod
    def get_total_contas():
        return Conta._total_contas

    def atualiza(self, taxa):
        self._saldo += self._saldo * taxa


class ContaCorrente(Conta):

    def atualiza(self, taxa):
        super().atualiza(taxa * 2)
