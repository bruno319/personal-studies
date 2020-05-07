# Container é qualquer objeto que contém um número arbitrário de outros objetos
# Listas, tuplas, conjuntos e dicionários são containers

from collections.abc import MutableSequence
from collections.abc import Sized
from collections.abc import Container
from collections.abc import Iterable
from typing import _T, overload


class Funcionarios(Container, Sized, Iterable):
    _dados = []

    def __contains__(self, __x: object) -> bool:
        return self._dados.__contains__(self, __x)

    def __len__(self) -> int:
        return len(self._dados)

    def __iter__(self):
        return self._dados.__iter__(self)


class FuncionariosMS(MutableSequence):
    _dados = []

    def __delitem__(self, i: int) -> None:
        del self._dados[i]

    def __getitem__(self, i: int) -> _T:
        return self._dados[i]

    def __setitem__(self, i: int, o: _T) -> None:
        self._dados[i] = o

    def __len__(self) -> int:
        return len(self._dados)

    def insert(self, index: int, object: _T) -> None:
        self._dados.insert(index, object)


if __name__ == '__main__':
    funcionarios = Funcionarios()
