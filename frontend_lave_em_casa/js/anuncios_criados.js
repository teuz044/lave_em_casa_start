
// Função para criar um card de anúncio
function criarCardAnuncio(anuncio) {
    var card = document.createElement('div');
    card.classList.add('card-anuncios');

    var titulo = document.createElement('h3');
    titulo.textContent = anuncio.titulo;
    card.appendChild(titulo);

    var descricao = document.createElement('p');
    descricao.textContent = anuncio.descricao, anuncio.proprietarioId;
    card.appendChild(descricao);

    var valorLavagem = document.createElement('p');
    valorLavagem.textContent = 'Valor da Lavagem: R$ ' + anuncio.valorLavagem.toFixed(2);
    card.appendChild(valorLavagem);
        
    return card;
}

// Função para exibir os anúncios na tela
function exibirAnuncios(anuncios) {
    var container = document.getElementById('anunciosContainer');

    anuncios.forEach(function (anuncio) {
        var card = criarCardAnuncio(anuncio);
        container.appendChild(card);
    });
}

// Buscar os anúncios da API via Fetch
fetch('http://localhost:8080/anuncios')
    .then(function (response) {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Erro na requisição');
        }
    })
    .then(function (anuncios) {
        exibirAnuncios(anuncios);
    })
    .catch(function (error) {
        console.error('Erro na requisição:', error);
    });

// Função para voltar para a tela de criação
function voltar() {
    window.location.href = 'index.html';
}

// Função para editar um anúncio
function editarAnuncio(proprietarioId, anuncioId) {
    // Redirecionar para a tela de edição com o ID do anúncio e do proprietário
    window.location.href = `editar-anuncio.html?proprietarioId=${proprietarioId}&anuncioId=${anuncioId}`;
}
