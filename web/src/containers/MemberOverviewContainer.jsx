import React from 'react';
import MemberOverview from '../components/MemberOverview/MemberOverview';

export default class MemberOverviewContainer extends React.Component {
  constructor() {
    super();
    this.state = {
      memberData: [],
    };
  }

  componentDidMount() {
    fetch('http://localhost:7000/members')
      .then(result => result.json())
      .then((data) => {
        this.setState({ memberData: data });
      });
  }

  handleAction = (action, memberData) => {
    if (action === 'save') {
      this.handleSave(memberData);
    } else if (action === 'delete') {
      this.handleDelete(memberData.id);
    }
  }

  handleDelete = (memberId) => {
    this.setState({ memberData: this.state.memberData.filter(item => memberId !== item.id) });
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
      <MemberOverview memberData={this.state.memberData} handleAction={this.handleAction}/>
    );
  }
}
