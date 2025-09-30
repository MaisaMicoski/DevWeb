package com.maisamicoski.projdevweb.controller;
import java.util.List;

public record ResultadoPaginado<T>(long totalDeItens, int totalDePagina, int paginaCorrente, List<T> itens) {
}
