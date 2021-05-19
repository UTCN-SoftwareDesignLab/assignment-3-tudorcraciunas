<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create consultation" : "Edit consultation" }}
        </v-toolbar>
        <v-form>
          <v-text-field
            v-if="!isNew"
            v-model="consultation.id"
            label="ID"
            readonly
          />
          <v-text-field v-model="consultation.description" label="description" />
          <v-text-field v-model="consultation.patientId" label="Patient" />
          <v-text-field v-model="consultation.doctorId" label="Doctor"/>
          <v-text-field v-model="consultation.startDate" label="Date" number/>
          <v-text-field v-model="consultation.address" label="Price" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
          <v-btn v-if="!isNew" v-on:click="deleteConsultation">Delete</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "ConsultationDialog",
  props: {
    consultation: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.consultation
          .create({//TODO: schimba parametrii la astea
            patientId: this.consultation.patientId,
            doctorId: this.consultation.doctorId,
            startDate: this.consultation.startDate,
            endDate: this.consultation.endDate,
            description: this.consultation.description,
          })
          .then(() => this.$emit("refresh"));
      } else {
        api.consultation
          .edit({
            id: this.consultation.id,
            patientId: this.consultation.patientId,
            doctorId: this.consultation.doctorId,
            startDate: this.consultation.startDate,
            endDate: this.consultation.endDate,
            description: this.consultation.description,
          })
          .then(() => this.$emit("refresh"));
      }
    },
    deleteConsultation() {
      api.consultation.deleteConsultation({
          id: this.consultation.id,
        })
        .then(() => this.$emit("refresh"));
    },
  },
  computed: {
    isNew: function () {
      return !this.consultation.id;
    },
  },
};
</script>

<style scoped></style>
