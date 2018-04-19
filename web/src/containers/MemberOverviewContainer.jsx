import React from 'react';
import MemberOverview from '../components/MemberOverview/MemberOverview';

export default class MemberOverviewContainer extends React.Component {
  constructor() {
    super();
    this.state = {
      memberData: [
        {
          id: 1,
          name: 'Willem Kersten',
          status: 'Current colleague',
        },
        {
          id: 2,
          name: 'Willem Veelenturf',
          status: 'Current colleague',
        },
        {
          id: 3,
          name: 'Maureen van der Sluis',
          status: 'Current colleague',
        },
        {
          id: 4,
          name: 'Vincent de Bruijn',
          status: 'Current colleague',
        },
        {
          id: 5,
          name: 'Jerre van Veluw',
          status: 'Current colleague',
        },
        {
          id: 6,
          name: 'Jordi Franken',
          status: 'Current colleague',
        },
        {
          id: 7,
          name: 'Ferdy van Varik',
          status: 'Future colleague',
        },
      ],
    };
  }

  handleSave = (editedMember) => {
    const data = this.state.memberData.map((item) => {
      const result = item.id === editedMember.id ? editedMember.data : item;
      return result;
    });

    this.setState({ memberData: data });
  }

  render() {
    return (
      <MemberOverview memberData={this.state.memberData} handleSave={this.handleSave}/>
    );
  }
}
