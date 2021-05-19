<template>
  <v-card>
    <v-card-title>
      Patients
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      <v-btn @click="addPatient">Add Patient</v-btn>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="patients"
      :search="search"
      @click:row="editPatient"
    ></v-data-table>
    <PatientDialog
      :opened="dialogVisible"
      :item="selectedItem"
      @refresh="refreshList"
    ></PatientDialog>

    <v-spacer></v-spacer>
  </v-card>
</template>

<script>
import api from "../api";
import PatientDialog from "../components/PatientDialog";

export default {
  name: "PatientList",
  components: { PatientDialog },
  data() {
    return {
      patients: [],
      search: "",
      headers: [
        {
          text: "ID",
          align: "start",
          sortable: false,
          value: "id",
        },
        { text: "Name", value: "name"},
        { text: "Card Number", value: "idCardNumber" },
        { text: "Numerical Code", value: "personalNumericalCode" },
        { text: "Birth Date", value: "birthDate" },
        { text: "Address", value: "address" },
      ],
      dialogVisible: false,
      selectedItem: {},
    };
  },
  methods: {
    editPatient(patient) {
      this.selectedItem = patient;
      this.dialogVisible = true;
    },
    addPatient() {
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedItem = {};
      this.patients = await api.patients.allPatients();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
