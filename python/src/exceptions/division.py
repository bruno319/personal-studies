def divide(n):
    try:
        return n / 0
    except ZeroDivisionError:
        print('divisão por zero')


def open_file():
    try:
        arquivo = open('palavras.txt', 'r')
    except IOError:
        print('não foi possível abrir o arquivo')
    else:
        print('o arquivo tem {} palavras'.format(len(arquivo.readlines())))
        arquivo.close()


class MeuErro(Exception):
    def __init__(self, valor):
        self.valor = valor

    def __str__(self):
        return repr(self.valor)


if __name__ == '__main__':
    try:
        # raise = throw
        raise MeuErro(2 * 2)
    except MeuErro as e:
        print('Minha exceção ocorreu, valor: {}'.format(e.valor))
