<h1>InfoVita - API de Estatísticas</h1>

<h2>1. Introdução rápida</h2>
API desenvolvida como parte de uma funcionalidade do software InfoVita. Tem como objetivo fornecer dados estatísticos sobre equipamentos hospitalares do estado do Piauí.

<h2>2. Endpoints</h2>

<h4>2.1. Estatísticas de um equipamento específico: GET /estatistica/equipamento/{código} </h4>
<p>Exemplo para: https://infovita.herokuapp.com/estatistica/equipamento/7 </p>

```json
{
  "codigo": 7,
  "nome": "Raio X Dentario",
  "existentes": 64,
  "emUso": 54,
  "existentesSUS": 31,
  "emUsoSus": 27
}
```

<h4>2.2. Estatísticas de todos os equipamentos: GET /estatistica/equipamento/all </h4>


<h2>3. Observações</h2>
<ul>
<li>O codigo do equipamento é o mesmo referenciado pelo DataSUS;</li>
<li>Os dados são referentes a capital Teresina;</li>
<li>Futuramente a pesquisa poderá ser expandida para outros município, ou talvez estados.</li>
</ul>


<h2>4. Links úteis</h2>
<p>API: https://infovita.herokuapp.com/</p>
<p>DataSUS: http://cnes2.datasus.gov.br/Mod_Ind_Equipamento.asp?VEstado=22&VMun=221100</p>
