from collections import namedtuple

Conta = namedtuple('Conta', 'numero titular saldo limite')
conta = Conta('123-4', 'João', 1000.0, 1000.0)

print(conta)            # saída: Conta(numero='123-4', titular='João', saldo=1000.0, limite=1000.0)

print(conta.titular)    # saída: João

print(conta[0])         # saída: '123-4'