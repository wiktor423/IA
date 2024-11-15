#___________________________________
#variables

linea_A_paradas = ["Alberti", "Pasco", "Congreso", "Saenz Peña", "Lima", "Avenida de Mayo", "Piedras", "Peru", "Plaza de Mayo"]

linea_B_paradas = ["Leandro N. Alem", "Florida", "Carlos Pellegrini", "Uruguay", "Callao", "Pasteur"]

linea_C_paradas = ["Avenida de Mayo", "Constitución", "Diagonal Norte", "General San Martin", "Independencia", "Lavalle", "Moreno", "Retiro", "San Juan"]

linea_D_paradas = ["Facultad de medicina", "Callao", "Tribunales", "9 de Julio", "Catedral"]

linea_E_paradas = ["Pichincha", "Entre Rios", "San Jose", "Independencia", "Belgrano", "Bolivar"]

#___________________________________
#clase

class Parada():
    def __init__(self, nombre, coordenadas):
        self.nombre = nombre
        self.coordenadas = coordenadas

    def getLinea(self):
        if self.nombre in linea_A_paradas:
            return 'A'

        elif self.nombre in linea_B_paradas:
            return 'B'
        
        elif self.nombre in linea_C_paradas:
            return 'C'
        
        elif self.nombre in linea_D_paradas:
            return 'D'
        
        elif self.nombre in linea_E_paradas:
            return 'E'
        
        else:
            return None