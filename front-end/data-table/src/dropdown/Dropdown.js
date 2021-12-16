import React, { Component } from 'react'
import Select from 'react-select'

const options = [
  { value: 'CAMEROON', label: 'Cameroon' },
  { value: 'ETHIOPIA', label: 'Ethiopia' },
  { value: 'MOROCCO', label: 'Morocco' },
  { value: 'MOZAMBIQUE', label: 'Mozambique' },
  { value: 'UGANDA', label: 'Uganda' }
]

const Dropdown = () => (
  <Select options={options} />
)

export default Dropdown