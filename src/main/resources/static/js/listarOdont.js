const apiURL = "http://localhost:8080";

// Obtener la referencia a la tabla y al modal
const tableBody = document.querySelector("#pacienteTable tbody");
const editModal = new bootstrap.Modal(document.getElementById("editModal"));
const editForm = document.getElementById("editForm");
let currentPacienteId;

// Función para obtener y mostrar los odontólogos
function fetchPacientes() {
  // listar los pacientes
  fetch(`${apiURL}/odontologo/listar`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      // Limpiar el contenido actual de la tabla
      tableBody.innerHTML = "";

      // Insertar los datos en la tabla
      data.forEach((odontologo, index) => {
        const row = document.createElement("tr");

        row.innerHTML = `
              <td>${odontologo.id}</td>
              <td>${odontologo.matricula}</td>
              <td>${odontologo.nombre}</td>
              <td>${odontologo.apellido}</td>
              <td>
                <button class="btn btn-primary btn-sm" onclick="editPaciente(${odontologo.id}, '${odontologo.matricula}','${odontologo.nombre}', '${odontologo.apellido}')">Modificar</button>
                <button class="btn btn-danger btn-sm" onclick="deletePaciente(${odontologo.id})">Eliminar</button>
              </td>
            `;

        tableBody.appendChild(row);
      });
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
}

// Función para abrir el modal y cargar los datos del paciente
editPaciente = function (
  id,
  matricula,
  nombre,
  apellido
) {
  currentPacienteId = id;
  document.getElementById("editMatricula").value = matricula;
  document.getElementById("editNombre").value = nombre;
  document.getElementById("editApellido").value = apellido;
  editModal.show();
};

// Función para editar un paciente
editForm.addEventListener("submit", function (event) {
  event.preventDefault();
  const matricula = document.getElementById("editMatricula").value;
  const nombre = document.getElementById("editNombre").value;
  const apellido = document.getElementById("editApellido").value;

  //modificar un paciente
  fetch(`${apiURL}/odontologo/modificar`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      id: currentPacienteId,
      matricula,
      nombre,
      apellido,
    }),
  })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      alert("Odontologo modificado con éxito");
      fetchPacientes();
      editModal.hide();
    })
    .catch((error) => {
      console.error("Error editando odontologo:", error);
    });
});

// Función para eliminar un paciente
deletePaciente = function (id) {
  if (confirm("¿Está seguro de que desea eliminar este odontologo?")) {
    // eliminar el paciente
    fetch(`${apiURL}/odontologo/eliminar/${id}`, {
      method: "DELETE",
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        alert("Odontologo eliminado con éxito");
        fetchPacientes();
      })
      .catch((error) => {
        console.error("Error borrando Odontologo:", error);
      });
  }
};

// Llamar a la función para obtener y mostrar los odontólogos
fetchPacientes();