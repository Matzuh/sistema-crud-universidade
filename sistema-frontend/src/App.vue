<template>
  <div class="container mt-5">
    <div class="p-4 mb-4 bg-dark text-white rounded shadow-sm">
      <h1 class="mb-0">🏫 Sistema Universitário - Painel de Controle</h1>
      <p class="mb-0 text-muted">Integração Real-time com API REST Spring Boot</p>
    </div>

    <ul class="nav nav-tabs mb-4">
      <li class="nav-item">
        <button class="nav-link font-weight-bold text-uppercase" :class="{ active: abaAtiva === 'estudantes' }" @click="abaAtiva = 'estudantes'">
          👨‍🎓 Estudantes
        </button>
      </li>
      <li class="nav-item">
        <button class="nav-link font-weight-bold text-uppercase" :class="{ active: abaAtiva === 'professores' }" @click="abaAtiva = 'professores'">
          👨‍🏫 Professores
        </button>
      </li>
    </ul>

    <div v-if="abaAtiva === 'estudantes'" class="row">
      <div class="col-md-4">
        <div class="card shadow-sm mb-4">
          <div class="card-header bg-primary text-white font-weight-bold">
            📝 Cadastrar Novo Aluno
          </div>
          <div class="card-body">
            <form @submit.prevent="cadastrarAluno">
              <div class="mb-3">
                <label class="form-label font-weight-bold">Nome Completo</label>
                <input v-model="novoAluno.name" type="text" class="form-control" placeholder="Digite seu nome completo" required />
              </div>
              <div class="mb-3">
                <label class="form-label font-weight-bold">E-mail</label>
                <input v-model="novoAluno.emailAddress" type="email" class="form-control" placeholder="Digite seu e-mail" required />
              </div>

              <hr />
              <h6 class="text-muted mb-3">📍 Endereço</h6>

              <div class="mb-3">
                <label class="form-label font-weight-bold">Rua</label>
                <input v-model="formEnderecoAluno.street" type="text" class="form-control" placeholder="Digite o nome da rua" required />
              </div>
              <div class="row">
                <div class="col-6 mb-3">
                  <label class="form-label font-weight-bold">CEP</label>
                  <input v-model="formEnderecoAluno.zipCode" type="text" class="form-control" placeholder="Digite seu CEP" required />
                </div>
                <div class="col-6 mb-3">
                  <label class="form-label font-weight-bold">Cidade</label>
                  <input v-model="formEnderecoAluno.city" type="text" class="form-control" placeholder="Digite sua cidade" required />
                </div>
              </div>
              <div class="mb-3">
                <label class="form-label font-weight-bold">Estado</label>
                <input v-model="formEnderecoAluno.state" type="text" class="form-control" placeholder="Digite seu estado" required />
              </div>

              <button type="submit" class="btn btn-success w-100 font-weight-bold">
                🚀 Salvar Aluno
              </button>
            </form>
          </div>
        </div>
      </div>

      <div class="col-md-8">
        <div class="card shadow-sm">
          <div class="card-header bg-secondary text-white d-flex justify-content-between align-items-center">
            <span class="font-weight-bold">📋 Alunos Matriculados</span>
            <button @click="buscarAlunos" class="btn btn-sm btn-light">🔄 Atualizar Lista</button>
          </div>
          <div class="card-body p-0">
            <div class="table-responsive">
              <table class="table table-hover table-striped mb-0">
                <thead class="table-dark">
                <tr>
                  <th>ID</th>
                  <th>Nome</th>
                  <th>E-mail</th>
                  <th>Localização</th>
                  <th class="text-center">Ações</th>
                </tr>
                </thead>
                <tbody>
                <tr v-if="alunos.length === 0">
                  <td colspan="5" class="text-center text-muted p-4">
                    Nenhum aluno cadastrado ou API fora do ar.
                  </td>
                </tr>
                <tr v-for="aluno in alunos" :key="aluno.id">
                  <td><strong>#{{ aluno.id }}</strong></td>
                  <td>{{ aluno.name }}</td>
                  <td>{{ aluno.emailAddress || 'Não informado' }}</td>
                  <td>{{ formatarEndereco(aluno.addresses) }}</td>
                  <td class="text-center">
                    <button @click="deletarAluno(aluno.id)" class="btn btn-danger btn-sm">
                      🗑️ Excluir
                    </button>
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div v-if="abaAtiva === 'professores'" class="row">
      <div class="col-md-4">
        <div class="card shadow-sm mb-4">
          <div class="card-header bg-info text-white font-weight-bold">
            📝 Cadastrar Novo Professor
          </div>
          <div class="card-body">
            <form @submit.prevent="cadastrarProfessor">
              <div class="mb-3">
                <label class="form-label font-weight-bold">Nome Completo</label>
                <input v-model="novoProfessor.name" type="text" class="form-control" placeholder="Digite seu nome completo" required />
              </div>
              <div class="mb-3">
                <label class="form-label font-weight-bold">E-mail</label>
                <input v-model="novoProfessor.emailAddress" type="email" class="form-control" placeholder="Digite seu e-mail" required />
              </div>
              <div class="mb-3">
                <label class="form-label font-weight-bold">Salário (R$)</label>
                <input v-model="novoProfessor.salary" type="number" step="0.01" class="form-control" placeholder="Ex: 5000.00" required />
              </div>

              <hr />
              <h6 class="text-muted mb-3">📍 Endereço</h6>

              <div class="mb-3">
                <label class="form-label font-weight-bold">Rua</label>
                <input v-model="formEnderecoProfessor.street" type="text" class="form-control" placeholder="Digite o nome da rua" required />
              </div>
              <div class="row">
                <div class="col-6 mb-3">
                  <label class="form-label font-weight-bold">CEP</label>
                  <input v-model="formEnderecoProfessor.zipCode" type="text" class="form-control" placeholder="Digite seu CEP" required />
                </div>
                <div class="col-6 mb-3">
                  <label class="form-label font-weight-bold">Cidade</label>
                  <input v-model="formEnderecoProfessor.city" type="text" class="form-control" placeholder="Digite sua cidade" required />
                </div>
              </div>
              <div class="mb-3">
                <label class="form-label font-weight-bold">Estado</label>
                <input v-model="formEnderecoProfessor.state" type="text" class="form-control" placeholder="Digite seu estado" required />
              </div>

              <button type="submit" class="btn btn-success w-100 font-weight-bold">
                🚀 Salvar Professor
              </button>
            </form>
          </div>
        </div>
      </div>

      <div class="col-md-8">
        <div class="card shadow-sm">
          <div class="card-header bg-secondary text-white d-flex justify-content-between align-items-center">
            <span class="font-weight-bold">📋 Corpo Docente (Professores)</span>
            <button @click="buscarProfessores" class="btn btn-sm btn-light">🔄 Atualizar Lista</button>
          </div>
          <div class="card-body p-0">
            <div class="table-responsive">
              <table class="table table-hover table-striped mb-0">
                <thead class="table-dark">
                <tr>
                  <th>ID</th>
                  <th>Nome</th>
                  <th>E-mail</th>
                  <th>Salário</th>
                  <th>Localização</th>
                  <th class="text-center">Ações</th>
                </tr>
                </thead>
                <tbody>
                <tr v-if="professores.length === 0">
                  <td colspan="6" class="text-center text-muted p-4">
                    Nenhum professor cadastrado ou API fora do ar.
                  </td>
                </tr>
                <tr v-for="professor in professores" :key="professor.id">
                  <td><strong>#{{ professor.id }}</strong></td>
                  <td>{{ professor.name }}</td>
                  <td>{{ professor.emailAddress || 'Não informado' }}</td>
                  <td>R$ {{ Number(professor.salary).toFixed(2) }}</td>
                  <td>{{ formatarEndereco(professor.addresses) }}</td>
                  <td class="text-center">
                    <button @click="deletarProfessor(professor.id)" class="btn btn-danger btn-sm">
                      🗑️ Excluir
                    </button>
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

const API_STUDENTS = 'http://localhost:8080/api/students'
const API_TEACHERS = 'http://localhost:8080/api/professors'

export default {
  data() {
    return {
      abaAtiva: 'estudantes',
      alunos: [],
      professores: [],
      novoAluno: {
        name: '',
        emailAddress: '',
        addresses: []
      },
      formEnderecoAluno: {
        street: '',
        city: '',
        state: '',
        zipCode: '',
        country: 'Brasil'
      },
      novoProfessor: {
        name: '',
        emailAddress: '',
        salary: '',
        addresses: []
      },
      formEnderecoProfessor: {
        street: '',
        city: '',
        state: '',
        zipCode: '',
        country: 'Brasil'
      }
    }
  },
  mounted() {
    this.buscarAlunos()
    this.buscarProfessores()
  },
  methods: {
    formatarEndereco(addresses) {
      if (!addresses || addresses.length === 0) return 'Não informado'
      const addr = addresses[0]
      return `${addr.street} - ${addr.city}/${addr.state} (CEP: ${addr.zipCode})`
    },

    async buscarAlunos() {
      try {
        const response = await axios.get(API_STUDENTS)
        this.alunos = response.data.content || response.data
      } catch (error) {
        console.error('Erro ao conectar com a API de Alunos:', error)
      }
    },
    async cadastrarAluno() {
      try {
        this.novoAluno.addresses = [{ ...this.formEnderecoAluno }]
        await axios.post(API_STUDENTS, this.novoAluno)
        this.buscarAlunos()
        this.resetarFormAluno()
      } catch (error) {
        alert('Erro ao cadastrar aluno.')
      }
    },
    async deletarAluno(id) {
      if (confirm('Deseja excluir este estudante?')) {
        try {
          await axios.delete(`${API_STUDENTS}/${id}`)
          this.buscarAlunos()
        } catch (error) {
          alert('Erro ao deletar aluno.')
        }
      }
    },
    resetarFormAluno() {
      this.novoAluno = { name: '', emailAddress: '', addresses: [] }
      this.formEnderecoAluno = { street: '', city: '', state: '', zipCode: '', country: 'Brasil' }
    },

    async buscarProfessores() {
      try {
        const response = await axios.get(API_TEACHERS)
        this.professores = response.data.content || response.data
      } catch (error) {
        console.error('Erro ao conectar com a API de Professores:', error)
      }
    },
    async cadastrarProfessor() {
      try {
        this.novoProfessor.addresses = [{ ...this.formEnderecoProfessor }]
        await axios.post(API_TEACHERS, this.novoProfessor)
        this.buscarProfessores()
        this.resetarFormProfessor()
      } catch (error) {
        alert('Erro ao cadastrar professor.')
      }
    },
    async deletarProfessor(id) {
      if (confirm('Deseja excluir este professor?')) {
        try {
          await axios.delete(`${API_TEACHERS}/${id}`)
          this.buscarProfessores()
        } catch (error) {
          alert('Erro ao deletar professor.')
        }
      }
    },
    resetarFormProfessor() {
      this.novoProfessor = { name: '', emailAddress: '', salary: '', addresses: [] }
      this.formEnderecoProfessor = { street: '', city: '', state: '', zipCode: '', country: 'Brasil' }
    }
  }
}
</script>

<style scoped>
.nav-tabs .nav-link {
  color: #495057;
  cursor: pointer;
}
.nav-tabs .nav-link.active {
  background-color: #f8f9fa;
  border-bottom-color: transparent;
}
.card {
  border: none;
}
th {
  font-weight: 600;
}
</style>