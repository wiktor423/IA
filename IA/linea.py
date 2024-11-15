class Linea():
    def __init__(self, lista, tiempo):
        self.lista = lista
        self.tiempo = tiempo

    def contains(self, parada):
        res = False
        for estacion in self.lista:
            if estacion == parada:
                res = True
        return res