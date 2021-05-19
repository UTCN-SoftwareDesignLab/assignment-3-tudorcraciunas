<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create patient" : "Edit patient" }}
        </v-toolbar>
        <v-form>

          <v-text-field v-model="patient.name" label="Name" />
          <v-text-field v-model="patient.idCardNumber" label="Identification Card" />
          <v-text-field v-model="patient.personalNumericalCode" label="PNC" />
          <v-text-field v-model="patient.birthDate" label="Birthdate" />
          <v-text-field v-model="patient.address" label="Address" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
          <v-btn v-if="!isNew" v-on:click="deletePatient">Delete</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "PatientDialog",
  props: {
    patient: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.patients
          .create({
            name: this.patient.name,
            idCardNumber: this.patient.idCardNumber,
            personalNumericalCode: this.patient.personalNumericalCode,
            birthDate: this.patient.birthDate,
            address: this.patient.address,
          })
          .then(() => this.$emit("refresh"));
      } else {
        api.patients
          .edit({
            id: this.patient.id,
            name: this.patient.name,
            idCardNumber: this.patient.idCardNumber,
            personalNumericalCode: this.patient.personalNumericalCode,
            birthDate: this.patient.birthDate,
            address: this.patient.address,
          })
          .then(() => this.$emit("refresh"));
      }
    },
    deletePatient() {
      api.patient
        .deletePatient({
          id: this.patient.id,
        })
        .then(() => this.$emit("refresh"));
    },
  },
  computed: {
    isNew: function () {
      return !this.patient.id;
    },
  },
};
</script>

<style scoped></style>
