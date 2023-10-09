import React from 'react';
import { ComponentMeta, ComponentStory } from '@storybook/react';
import { UserCard, UserCardProps } from '../containers/UserCard/UserCard';

const props: UserCardProps = {
  userId: 'asd87f6as9d87f6afsdf',
  name: 'Tumanov Maksim',
  sum: -457,
};

export default {
  title: 'Example/UserCard',
  component: UserCard,
  argTypes: {},
} as ComponentMeta<typeof UserCard>;

const Template: ComponentStory<typeof UserCard> = (args) => (
  <UserCard {...props} />
);

export const Primary = Template.bind({});
// More on args: https://storybook.js.org/docs/react/writing-stories/args
Primary.args = {};
