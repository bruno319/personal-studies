class Conta:

    # quando definido fora do construtor, o atributo é estático
    _total_contas = 0

    # proibe a criação de atributos em tempo de execução
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